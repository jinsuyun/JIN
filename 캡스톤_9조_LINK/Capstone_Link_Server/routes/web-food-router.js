var express = require('express');
var router = express.Router();
var webFoodAdapter = require('../adapters/webfood-db-adapter');

router.get('/', function(req, res) {
    webFoodAdapter.foodSearch(function(err,rows){
        res.json(rows);
    });
});

module.exports = router;
