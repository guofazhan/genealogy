/**
 * 登录测试数据
 * @type {{"POST /user/login": (function(*, *): *), "GET /user/info": (function(*, *): *), "POST /user/logout": (function(*, *): *), [p: string]: *}}
 */
module.exports = {
  [`POST /user/login`]: (req, res) => {
    console.log('/user/login---->',req.body);
    let name = req.body.username
    let password = req.body.password

    if(name !== 'admin'){
      return res.json({
        "code":30000,
        "message":"username is not admin"
      });
    }
    if(password !== '123456'){
      return res.json({
        "code":30001,
        "message":"password is not 123456"
      });
    }
    return res.json({
      "code":20000,
      "data":{
        "token":"admin"
      }
    });
  },
  [`GET /user/info`]: (req, res) => {
    console.log('/user/info---->', req.params);
    return res.json({
      "code":20000,
      "data":{
        "roles":[
          "admin"
        ],
        "name":"测试用户",
        "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
      }
    });
  },
  [`POST /user/logout`]: (req, res) => {
    console.log('/user/logout---->', req.params);
    return res.json({
      "code":20000,
      "data":"success"
    });
  }
}
