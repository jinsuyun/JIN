var express = require('express');
var router = express.Router();
var webWorkoutAdapter = require('../adapters/webworkout-db-adapter');

router.get('/', function(req, res) {
    webWorkoutAdapter.workoutSearch(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
