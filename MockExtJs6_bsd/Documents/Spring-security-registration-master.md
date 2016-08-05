=========

## Login and Registration Example Project with Spring Security

[Source @ GitHub](https://github.com/eugenp/spring-security-registration)
---

### Relevant Articles: 
- [Spring Security Registration Tutorial](http://www.baeldung.com/spring-security-registration)
- [The Registration Process With Spring Security](http://www.baeldung.com/registration-with-spring-mvc-and-spring-security)
- [Registration – Activate a New Account by Email](http://www.baeldung.com/registration-verify-user-by-email)
- [Registration with Spring Security – Password Encoding](http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt)
- [Spring Security – Roles and Privileges](http://www.baeldung.com/role-and-privilege-for-spring-security-registration)
- [Prevent Brute Force Authentication Attempts with Spring Security](http://www.baeldung.com/spring-security-block-brute-force-authentication-attempts)
- The "*Password Reset Token*" [Spring Security – Reset Your Password](http://www.baeldung.com/spring-security-registration-i-forgot-my-password)
- [Spring Security Registration – Resend Verification Email](http://www.baeldung.com/spring-security-registration-verification-email)
- [The Registration API becomes RESTful](http://www.baeldung.com/registration-restful-api)
- [Registration – Password Strength and Rules](http://www.baeldung.com/registration-password-strength-and-rules)
- [Updating your Password](http://www.baeldung.com/updating-your-password/)
- I was very surprised to learn that **Spring actually does not support the roles/rights** security approach. Spring Security has two basic security approaches.
	1. Simple, role-based security without rights.
	2. Complex ACL based security that defines permissions at the domain object level. 
Most applications need way less than the complex approach, but more than the simple approach that uses just roles and doesn't have a roles to rights mapping like we want to. Read more [Spring 3(+) Security - authorization with roles and right](http://en.tekstenuitleg.net/blog/spring-security-with-roles-and-rights)

### read about password 
 - [password reset token best practices](http://stackoverflow.com/questions/2734367/implement-password-recovery-best-practice)
```
Here's an example of how someone did it with Node.js, basically generate a random token, an expiry time, send out the link with the token attached, have a reset/:token route that ensures a user exists with that token (which is also not expired) and, if so, redirect to a reset password page.
http://sahatyalkabov.com/how-to-implement-password-reset-in-nodejs/
```

### Build the Project
```
mvn clean install
```


### Set up MySQL
```
mysql -u root -p 
> CREATE USER 'tutorialuser'@'localhost' IDENTIFIED BY 'tutorialmy5ql';
> GRANT ALL PRIVILEGES ON *.* TO 'tutorialuser'@'localhost';
> FLUSH PRIVILEGES;
```
initial admin log-in
email: test@test.com
pswd: test
Setup database see org.baeldung.spring.SetupDataLoader


### Set up Email

You need to configure the email by renaming file "email.properties.sample" to "email.properties" and provide your own username and password.
You also need to use your own host, you can use Amazon or Google for example.
