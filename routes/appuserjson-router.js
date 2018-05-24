var express = require('express');
var router = express.Router();
var appuserAdapter = require('../adapters/appuserjson-db-adapter');

router.get('/', function(req, res) {
    appuserAdapter.userSearch(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
