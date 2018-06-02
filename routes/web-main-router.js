var express = require('express');
var router = express.Router();
var webFoodAdapter = require('../adapters/webmain-db-adapter');

router.get('/', function(req, res) {

    var id=req.params.id;
    res.render('staty.html');
});

module.exports = router;
