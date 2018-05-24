var express = require('express');
var router = express.Router();

router.get('/', function(req, res) {

    var id=req.params.id;
    res.render('graph.html');
});

module.exports = router;
