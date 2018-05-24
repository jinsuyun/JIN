var express = require('express');
var router = express.Router();
var trainerAdapter = require('../adapters/trainer-db-adapter');

router.get('/', function(req, res) {
    trainerAdapter.trainerSearch(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
