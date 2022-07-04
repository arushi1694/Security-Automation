# Security-Automation

Test Cases: -   

1. Missing Security headers  
Recommendation: Ensure that application/web server sets the response header appropriately,  
1: “X-Content-Type-Options : ‘no sniff' 
2: Strict-Transport-Security: max-age=31536000;includeSubdomains 
3: X-XSS-Protection: 1; mode=block 
4: X-Frame-Options: deny   

2. HTTP Methods:  
Recommendation: Ensure that application/web server only accept POST, HEAD, GET, PUT & PATCH Method and also TRACE, DELETE should be disabled  

3. Insufficient Anti-Automation (Login Page, OTP Page):  
Recommendation: Ensure that Rate limiting should be implemented on all sensitive APIs.  

4. Version Disclosure:  Recommendation: Ensure that web server prevent  information leakage (Server Version) from the SERVER header of its HTTP response.
