var express = require('express');
var router = express.Router();
var webDailyAdapter = require('../adapters/webdaily-db-adapter');

router.get('/', function(req, res) {
    webDailyAdapter.dailySearch(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
