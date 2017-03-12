/*
SELECT * FROM users u JOIN authorities auth on u.id = auth.userId

Folowing statementes are equal
*/

SELECT u.id, USER_TYPE, u.userId, email, firstName, lastName, role FROM users u JOIN authorities auth on u.id = auth.userId
/* duplicate join clause: where u.id = auth.userId */

SELECT u.id, USER_TYPE, u.userId, email, firstName, lastName, role FROM users u, authorities auth WHERE u.id = auth.userId

/*
Return same collection
u.id, USER_TYPE,  u.userId,           email,              firstName,  lastName, role
1   	QuotaUser   Alexei Ptitchkin		                    Alexei      Ptitchkin	ROLE_QuotaKPI_COMPANY
1     QuotaUser   Alexei Ptitchkin		                    Alexei	    Ptitchkin	ROLE_QuotaKPI_QUOTA
1   	QuotaUser   Alexei Ptitchkin		                    Alexei	    Ptitchkin	ROLE_QuotaKPI_BUDGET
1   	QuotaUser   Alexei Ptitchkin		                    Alexei	    Ptitchkin	ROLE_QuotaKPI_ADMIN
2   	BsdUser     Tim Adams           timAdams@gmail.com	Tim	        Adams	    ROLE_BSD_DEALER
3   	BsdUser     Hong Li             hongLi@gmail.com	  Hong	      Li	      ROLE_BSD_DEALER
4   	BsdUser     Thyme Leaf	        thymeLeaf@gmail.com	Thyme	      Leaf	    ROLE_BSD_DEALER_THYMELEAF
5   	BsdUser     Angular User      	angular@gmail.com   Angular	    User	    ROLE_QuotaKPI_ADMIN_ANGULAR
*/


SELECT distinct role FROM authorities auth

/*
    ROLE_QuotaKPI_COMPANY
    ROLE_QuotaKPI_QUOTA
    ROLE_QuotaKPI_BUDGET
    ROLE_QuotaKPI_ADMIN
    ROLE_BSD_DEALER
    ROLE_BSD_DEALER_THYMELEAF
    ROLE_QuotaKPI_ADMIN_ANGULAR
*/


