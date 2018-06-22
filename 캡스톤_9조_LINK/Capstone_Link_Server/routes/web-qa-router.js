var express = require('express');
var router = express.Router();
var webFoodAdapter = require('../adapters/webqa-db-adapter');

router.get('/', function(req, res) {

    var id=req.params.id;
    res.render('post.html');
});

module.exports = router;
