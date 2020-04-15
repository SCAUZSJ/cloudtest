# cloudtest



oauth:
```
GET CODE: GET localhost:8020/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://localhost:9903/callback&scope=server
GET TOKEN: POST localhost:8020/oauth/token?code=?&grant_type=authorization_code&redirect_uri=http://localhost:9903/callback&scope=server&client_id=client&client_secret=secret
GET USER: GET localhost:8020/user/getUserInfo?access_token=?

```