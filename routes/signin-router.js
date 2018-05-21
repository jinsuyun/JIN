var express = require('express');
var router = express.Router();
var loginAdapter =require('../adapters/signin-db-adapter.js');

router.post('/', function(req, res) {

    console.log(req.body);
    loginKey = req.body.key; // facebook로그인시 받아오는 키

    loginAdapter.loginSearch(loginKey);

});

module.exports = router;
