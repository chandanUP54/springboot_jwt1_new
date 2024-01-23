package com.example.securitymain.entities;

public enum Role {
USER,
ADMIN
}


/*  If your roles are stored in the database without the "ROLE_" prefix 
(e.g., "USER" instead of "ROLE_USER"), you should use hasRole and hasAnyRole 
instead of hasAuthority and hasAnyAuthority. 
In your case, it seems like you are using hasAuthority and hasAnyAuthority, 
so make sure your roles in the database include the "ROLE_" prefix.  */

