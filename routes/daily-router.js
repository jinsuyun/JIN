var express = require('express');
var router = express.Router();
var dailyAdapter = require('../adapters/daily-db-adapter');

router.post('/', function(req, res) {
    dailyAdapter.dailyWrite(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
