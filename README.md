# jwt
Creating JWT token, verifying and retrieving the claims


JWT(Json Web Tokens): header.payload.signature

**header** = {
  
  "typ": "JWT",
  "alg": "HMAC256"

}
================

**payload (claims):**


- contains the information which we needs to transmit.
- contains the information related to token itself.
- Information is JSON represenatation of claims(key:value)

==============

**Signature :**

- A hash of header and payload using a secret.

=============

**Example :**

 var s = base64Encode(header)+"."+base64enocde(payload);
 
 var signature = hashAlgHs256(s, 'secretkey');
 
 var jwt = s + "." + base64Encode(signature)
 ============

The Java Library used in pom.xml is:

   <dependency>
    		<groupId>com.auth0</groupId>
    		<artifactId>java-jwt</artifactId>
    		<version>3.4.1</version>
		</dependency>
		
		
In JwtTokens.java run the main methods which creates the token using HMAC256 and later verifies the Token and we third is getting claims we set while generating the tokens.
