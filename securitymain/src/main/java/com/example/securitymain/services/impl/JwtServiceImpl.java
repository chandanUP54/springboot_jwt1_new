
package com.example.securitymain.services.impl;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.securitymain.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	
    public static final String SECRET = "vbfjfjdsdsdjdcdvj24487676767672112555551cbcvveeuefhfhb7747477474c7"; 

	public String generateToken(UserDetails userDetails) {
       
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails) {
       
		return Jwts.builder().setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24*7))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}


	
	private <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	} 
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	//give username from token  also check token is expired or not
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
    }
	
	private boolean isTokenExpired(String token) {
		return  extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }
	
	

}
