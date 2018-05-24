var express = require('express');
var router = express.Router();
var webdailyAdapter = require('../adapters/webdaily-db-adapter');

router.get('/', function(req, res) {
    webdailyAdapter.dailySearch(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
