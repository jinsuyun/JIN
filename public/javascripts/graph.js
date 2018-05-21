$(document).ready(function () {
    var myapi = "http://localhost:3001/appuserjson"
    var appuser_name = [];
    var appuser_id=[];
    var bmi = [];
    $.getJSON(myapi, function (info) {
        $.each(info, function (key, item) {
            appuser_name[key] = item["name"];
            appuser_id[key]=item["id"];
            $(".dropdown-menu").append("<li value=" + appuser_name[key] + "><a value=" + appuser_name[key] + ">" + appuser_name[key] + "</a></li>");
        });
    });
});

$(document).ready(function () {
    $('#dropdown_menu_id').on("click",function (req, res) {
        
        var appuserapi = "http://localhost:3001/appuserjson"
        var dailyapi = "http://localhost:3001/dailyjson"
        var name=$('#dropdown_menu_id').text();
        alert(name);
        $.getJSON(appuserapi, function (info) {
            var appuser = [];
            var bmi = [];
            $.each(info, function (key, item) {
                appuser[key] = item;
                bmi[key] = item.weight / ((item.height / 100) * (item.height / 100));
                bmi[key] = bmi[key].toFixed(2);

                var bmi_chart = AmCharts.makeChart("bmi", {
                    "type": "serial",
                    "rotate": true,
                    "theme": "light",
                    "autoMargins": false,
                    "marginTop": 30,
                    "marginLeft": 80,
                    "marginBottom": 30,
                    "marginRight": 50,
                    "titles": [{
                        "text": "B M I"
                    }],
                    "legend": {
                        "horizontalGap": 1,
                        "maxColumns": 1,
                        "position": "right",
                        "useGraphSettings": true,
                        "markerSize": 10
                    },
                    "dataProvider": [{
                        "category": "BMI",
                        "low_weight": 18.5,
                        "normal": 4.5,
                        "over_weight": 2,
                        "obsecity": 5,
                        "high_obsecity": 5,
                        "full": 35,
                        "bullet": bmi[key],
                    }],
                    "valueAxes": [{
                        "minimum": 14,
                        "maximum": 35,
                        "stackType": "regular",
                        "gridAlpha": 0
                    }],
                    "startDuration": 1,
                    "graphs": [{
                        "fillAlphas": 0.8,
                        "lineColor": "#19d228",
                        "showBalloon": false,
                        "type": "column",
                        "valueField": "low_weight",
                        "title": "저체중"
                        }, {
                        "fillAlphas": 0.8,
                        "lineColor": "#f4fb16",
                        "showBalloon": false,
                        "type": "column",
                        "valueField": "normal",
                        "title": "정상"
                        }, {
                        "fillAlphas": 0.8,
                        "lineColor": "#f6d32b",
                        "showBalloon": false,
                        "type": "column",
                        "valueField": "over_weight",
                        "title": "과체중"
                        }, {
                        "fillAlphas": 0.8,
                        "lineColor": "#fb7116",
                        "showBalloon": false,
                        "type": "column",
                        "valueField": "obsecity",
                        "title": "비만"
                        }, {
                        "fillAlphas": 0.8,
                        "lineColor": "#ff0000",
                        "showBalloon": false,
                        "type": "column",
                        "valueField": "high_obsecity",
                        "title": "고도 비만"
                        }, {
                        "clustered": false,
                        "columnWidth": 0.3,
                        "fillAlphas": 1,
                        "lineColor": "#0027ff",
                        "stackable": false,
                        "type": "column",
                        "valueField": "bullet",
                        "title":"자신의 BMI"
                        }],
                    "columnWidth": 1,
                    "categoryField": "category",
                    "categoryAxis": {
                        "gridAlpha": 0,
                        "position": "left"
                    }
                });
            });
        });
        $.getJSON(dailyapi, function (info) {
            $.each(info, function (key, item) {
                var part_chart = AmCharts.makeChart("part", {
                    "type": "radar",
                    "theme": "light",
                    "addClassNames": true,
                    "fontSize": 13,
                    "titles": [{
                        "text": "부위별 세트"
                    }],
                    "dataProvider": [{
                        "country": "팔",
                        "litres": info[key]["arm"],
                        "color": "#67b7dc"
                        }, {
                        "country": "하체",
                        "litres": info[key]["leg"],
                        "color": "#fdd400"
                        }, {
                        "country": "등",
                        "litres": info[key]["back"],
                        "color": "#84b761"
                        }, {
                        "country": "가슴",
                        "litres": info[key]["chest"],
                        "color": "#cc4748"
                        }, {
                        "country": "어깨",
                        "litres": info[key]["shoulder"],
                        "color": "#cd82ad"
                        }, {
                        "country": "복근",
                        "litres": info[key]["sixpack"],
                        "color": "#2f4074"
                        }],
                    "valueAxes": [{
                        "axisTitleOffset": 20,
                        "minimum": 0,
                        "axisAlpha": 0.15
                    }],
                    "startDuration": 2,
                    "graphs": [{
                        "balloonText": "[[value]] 세트",
                        "bullet": "round",
                        "lineThickness": 2,
                        "valueField": "litres"
                    }],
                    "categoryField": "country",
                    "listeners": [{
                        "event": "rendered",
                        "method": updateLabels
                    }, {
                        "event": "resized",
                        "method": updateLabels
                    }]
                });
                var sum_week_run = [];
                console.log(info[key]["id"],info[key]["workoutday"]);
                var day=new Date(info[key]["workoutday"]).getDay();
                console.log(day);

                sum_week_run[key] = (info[key]["mon_run"] + info[key]["tue_run"] + info[key]["wed_run"] + info[key]["thu_run"] + info[key]["fri_run"] + info[key]["sat_run"] + info[key]["sun_run"]) / 60;
                sum_week_run[key] = sum_week_run[key].toFixed(2);

                var sum_week_weight = [];
                sum_week_weight[key] = (info[key]["mon_weight"] + info[key]["tue_weight"] + info[key]["wed_weight"] + info[key]["thu_weight"] + info[key]["fri_weight"] + info[key]["sat_weight"] + info[key]["sun_weight"]) / 60
                sum_week_weight[key] = sum_week_weight[key].toFixed(2);

                var month_chart = AmCharts.makeChart("month", {
                    "type": "serial",
                    "theme": "light",
                    "titles": [{
                        "text": "월간 운동량"
                    }],
                    "dataProvider": [{
                        "country": "1주차",
                        "running": sum_week_run[key],
                        "weight": sum_week_weight[key]
                        }, {
                        "country": "2주차",
                        "running": sum_week_run[key],
                        "weight": sum_week_weight[key]
                        }, {
                        "country": "3주차",
                        "running": sum_week_run[key],
                        "weight": sum_week_weight[key],
                        }, {
                        "country": "4주차",
                        "running": sum_week_run[key],
                        "weight": sum_week_weight[key],
                        }],
                    "graphs": [{
                        "fillAlphas": 0.9,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "valueField": "running",
                        "balloonText": "유산소:[[value]]시간",
                        }, {
                        "fillAlphas": 0.9,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "valueField": "weight",
                        "balloonText": "웨이트:[[value]]시간",
                    }],
                    "categoryField": "country",
                    "chartCursor": {
                        "fullWidth": true,
                        "cursorAlpha": 0.1,
                        "listeners": [{
                            "event": "changed",
                            "method": function (ev) {
                                // Log last cursor position
                                ev.chart.lastCursorPosition = ev.index;
                            }
                        }]
                    }
                });

                var week_chart = AmCharts.makeChart("week", {
                    "type": "serial",
                    "theme": "light",
                    "legend": {
                        "horizontalGap": 10,
                        "maxColumns": 1,
                        "position": "right",
                        "useGraphSettings": true,
                        "markerSize": 10
                    },
                    "titles": [{
                        "text": "주간 운동량"
                    }],
                    "dataProvider": [{
                        "country": "월",
                        "running": info[key]["mon_run"],
                        "weight": info[key]["mon_weight"],
                        }, {
                        "country": "화",
                        "running": info[key]["tue_run"],
                        "weight": info[key]["tue_weight"],
                        }, {
                        "country": "수",
                        "running": info[key]["wed_run"],
                        "weight": info[key]["wed_weight"],
                        }, {
                        "country": "목",
                        "running": info[key]["thu_run"],
                        "weight": info[key]["thu_weight"],
                        }, {
                        "country": "금",
                        "running": info[key]["fri_run"],
                        "weight": info[key]["fri_weight"],
                        }, {
                        "country": "토",
                        "running": info[key]["sat_run"],
                        "weight": info[key]["sat_weight"],
                        }, {
                        "country": "일",
                        "running": info[key]["sun_run"],
                        "weight": info[key]["sun_weight"],
                        }],
                    "graphs": [{
                        "balloonText": "유산소:[[value]]분",
                        "fillAlphas": 0.9,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "title": "유산소",
                        "valueField": "running"
                    }, {
                        "balloonText": "웨이트:[[value]]분",
                        "fillAlphas": 0.9,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "title": "무산소",
                        "valueField": "weight"
                    }],
                    "categoryField": "country",
                    "chartCursor": {
                        "fullWidth": true,
                        "cursorAlpha": 0.1,
                        "listeners": [{
                            "event": "changed",
                            "method": function (ev) {
                                // Log last cursor position
                                ev.chart.lastCursorPosition = ev.index;
                            }
                        }]
                    }
                });

                var workout_calories_chart = AmCharts.makeChart("workout_calories", {
                    "type": "pie",
                    "theme": "none",
                    "innerRadius": "40%",
                    "gradientRatio": [-0.4, -0.4, -0.4, -0.4, -0.4, -0.4, 0, 0.1, 0.2, 0.1, 0, -0.2, -0.5],
                    "titles": [{
                        "text": "소모 칼로리량"
                    }],
                    "dataProvider": [{
                        "country": "소모한 칼로리",
                        "litres": info[key]["spent_calories"]
                    }, {
                        "country": "소모해야할 칼로리",
                        "litres": info[key]["all_spent_calories"] - info[key]["spent_calories"]
                    }],
                    "balloonText": "[[value]]",
                    "valueField": "litres",
                    "titleField": "country",
                    "balloon": {
                        "drop": true,
                        "adjustBorderColor": false,
                        "color": "#FFFFFF",
                        "fontSize": 16
                    },
                    "export": {
                        "enabled": true
                    }
                });



                var eat_calories_chart = AmCharts.makeChart("eat_calories", {
                    "type": "pie",
                    "theme": "none",
                    "innerRadius": "40%",
                    "gradientRatio": [-0.4, -0.4, -0.4, -0.4, -0.4, -0.4, 0, 0.1, 0.2, 0.1, 0, -0.2, -0.5],
                    "titles": [{
                        "text": "섭취 칼로리량"
                    }],
                    "dataProvider": [{
                        "country": "섭취한 칼로리",
                        "litres": info[key]["eat_calories"]
                        }, {
                        "country": "섭취해야할 칼로리",
                        "litres": info[key]["all_eat_calories"] - info[key]["eat_calories"]
                        }],
                    "balloonText": "[[value]]",
                    "valueField": "litres",
                    "titleField": "country",
                    "balloon": {
                        "drop": true,
                        "adjustBorderColor": false,
                        "color": "#FFFFFF",
                        "fontSize": 16
                    },
                    "export": {
                        "enabled": true
                    }
                });
            });
        });
        var weight_chart = AmCharts.makeChart("weight", {
            "type": "serial",
            "theme": "light",
            "marginRight": 40,
            "marginLeft": 40,
            "autoMarginOffset": 20,
            "mouseWheelZoomEnabled": true,
            "dataDateFormat": "YYYY-MM-DD",
            "valueAxes": [{
                "id": "v1",
                "axisAlpha": 0,
                "position": "left",
                "ignoreAxisWidth": true
                    }],
            "balloon": {
                "borderThickness": 1,
                "shadowAlpha": 0
            },
            "graphs": [{
                "id": "g1",
                "balloon": {
                    "drop": true,
                    "adjustBorderColor": false,
                    "color": "#ffffff"
                },
                "bullet": "round",
                "bulletBorderAlpha": 1,
                "bulletColor": "#FFFFFF",
                "bulletSize": 5,
                "hideBulletsCount": 50,
                "lineThickness": 2,
                "title": "red line",
                "useLineColorForBulletBorder": true,
                "valueField": "value",
                "balloonText": "<span style='font-size:18px;'>[[value]]</span>"
                    }],
            "categoryField": "date",
            "categoryAxis": {
                "parseDates": true,
                "dashLength": 1,
                "minorGridEnabled": true
            },
            "export": {
                "enabled": true
            },
            "titles": [{
                "text": "일일 체중 변화"
                    }],
            "dataProvider": [{
                "date": "2012-07-27",
                "value": 13
                        }, {
                "date": "2012-07-28",
                "value": 11
                        }, {
                "date": "2012-07-29",
                "value": 15
                        }, {
                "date": "2012-07-30",
                "value": 16
                        }, {
                "date": "2012-07-31",
                "value": 18
                        }, {
                "date": "2012-08-01",
                "value": 13
                        }, {
                "date": "2012-08-02",
                "value": 22
                        }, {
                "date": "2012-08-03",
                "value": 23
                        }, {
                "date": "2012-08-04",
                "value": 20
                        }, {
                "date": "2012-08-05",
                "value": 17
                        }, {
                "date": "2012-08-06",
                "value": 16
                        }, {
                "date": "2012-08-07",
                "value": 18
                        }, {
                "date": "2012-08-08",
                "value": 21
                        }, {
                "date": "2012-08-09",
                "value": 26
                        }, {
                "date": "2012-08-10",
                "value": 24
                        }, {
                "date": "2012-08-11",
                "value": 29
                        }, {
                "date": "2012-08-12",
                "value": 32
                        }, {
                "date": "2012-08-13",
                "value": 18
                        }, {
                "date": "2012-08-14",
                "value": 24
                        }, {
                "date": "2012-08-15",
                "value": 22
                        }, {
                "date": "2012-08-16",
                "value": 18
                        }, {
                "date": "2012-08-17",
                "value": 19
                        }, {
                "date": "2012-08-18",
                "value": 14
                        }, {
                "date": "2012-08-19",
                "value": 15
                        }, {
                "date": "2012-08-20",
                "value": 12
                        }, {
                "date": "2012-08-21",
                "value": 8
                        }, {
                "date": "2012-08-22",
                "value": 9
                        }, {
                "date": "2012-08-23",
                "value": 8
                        }, {
                "date": "2012-08-24",
                "value": 7
                        }, {
                "date": "2012-08-25",
                "value": 5
                        }, {
                "date": "2012-08-26",
                "value": 11
                        }]
        });

        weight_chart.addListener("rendered", zoomChart);

        zoomChart();

        function zoomChart() {
            weight_chart.zoomToIndexes(weight_chart.dataProvider.length - 40, weight_chart.dataProvider.length - 1);
        }
    });
});

function updateLabels(event) {
    var labels = event.chart.chartDiv.getElementsByClassName("amcharts-axis-title");
    for (var i = 0; i < labels.length; i++) {
        var color = event.chart.dataProvider[i].color;
        if (color !== undefined) {
            labels[i].setAttribute("fill", color);
        }
    }
}

var chartData = {
    "1995": [
        {
            "sector": "Agriculture",
            "size": 6.6
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.6
        },
        {
            "sector": "Manufacturing",
            "size": 23.2
        },
        {
            "sector": "Electricity and Water",
            "size": 2.2
        },
        {
            "sector": "Construction",
            "size": 4.5
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 14.6
        },
        {
            "sector": "Transport and Communication",
            "size": 9.3
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 22.5
        }],
    "1996": [
        {
            "sector": "Agriculture",
            "size": 6.4
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.5
        },
        {
            "sector": "Manufacturing",
            "size": 22.4
        },
        {
            "sector": "Electricity and Water",
            "size": 2
        },
        {
            "sector": "Construction",
            "size": 4.2
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 14.8
        },
        {
            "sector": "Transport and Communication",
            "size": 9.7
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 22
        }],
    "1997": [
        {
            "sector": "Agriculture",
            "size": 6.1
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 20.9
        },
        {
            "sector": "Electricity and Water",
            "size": 1.8
        },
        {
            "sector": "Construction",
            "size": 4.2
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 13.7
        },
        {
            "sector": "Transport and Communication",
            "size": 9.4
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 22.1
        }],
    "1998": [
        {
            "sector": "Agriculture",
            "size": 6.2
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 21.4
        },
        {
            "sector": "Electricity and Water",
            "size": 1.9
        },
        {
            "sector": "Construction",
            "size": 4.2
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 14.5
        },
        {
            "sector": "Transport and Communication",
            "size": 10.6
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 23
        }],
    "1999": [
        {
            "sector": "Agriculture",
            "size": 5.7
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 20
        },
        {
            "sector": "Electricity and Water",
            "size": 1.8
        },
        {
            "sector": "Construction",
            "size": 4.4
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.2
        },
        {
            "sector": "Transport and Communication",
            "size": 10.5
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 24.7
        }],
    "2000": [
        {
            "sector": "Agriculture",
            "size": 5.1
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 20.4
        },
        {
            "sector": "Electricity and Water",
            "size": 1.7
        },
        {
            "sector": "Construction",
            "size": 4
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.3
        },
        {
            "sector": "Transport and Communication",
            "size": 10.7
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 24.6
        }],
    "2001": [
        {
            "sector": "Agriculture",
            "size": 5.5
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 20.3
        },
        {
            "sector": "Electricity and Water",
            "size": 1.6
        },
        {
            "sector": "Construction",
            "size": 3.1
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.3
        },
        {
            "sector": "Transport and Communication",
            "size": 10.7
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 25.8
        }],
    "2002": [
        {
            "sector": "Agriculture",
            "size": 5.7
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 20.5
        },
        {
            "sector": "Electricity and Water",
            "size": 1.6
        },
        {
            "sector": "Construction",
            "size": 3.6
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.1
        },
        {
            "sector": "Transport and Communication",
            "size": 10.7
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 26
        }],
    "2003": [
        {
            "sector": "Agriculture",
            "size": 4.9
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 19.4
        },
        {
            "sector": "Electricity and Water",
            "size": 1.5
        },
        {
            "sector": "Construction",
            "size": 3.3
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.2
        },
        {
            "sector": "Transport and Communication",
            "size": 11
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 27.5
        }],
    "2004": [
        {
            "sector": "Agriculture",
            "size": 4.7
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 18.4
        },
        {
            "sector": "Electricity and Water",
            "size": 1.4
        },
        {
            "sector": "Construction",
            "size": 3.3
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.9
        },
        {
            "sector": "Transport and Communication",
            "size": 10.6
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 28.1
        }],
    "2005": [
        {
            "sector": "Agriculture",
            "size": 4.3
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 18.1
        },
        {
            "sector": "Electricity and Water",
            "size": 1.4
        },
        {
            "sector": "Construction",
            "size": 3.9
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.7
        },
        {
            "sector": "Transport and Communication",
            "size": 10.6
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 29.1
        }],
    "2006": [
        {
            "sector": "Agriculture",
            "size": 4
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 16.5
        },
        {
            "sector": "Electricity and Water",
            "size": 1.3
        },
        {
            "sector": "Construction",
            "size": 3.7
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 14.2
        },
        {
            "sector": "Transport and Communication",
            "size": 12.1
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 29.1
        }],
    "2007": [
        {
            "sector": "Agriculture",
            "size": 4.7
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 16.2
        },
        {
            "sector": "Electricity and Water",
            "size": 1.2
        },
        {
            "sector": "Construction",
            "size": 4.1
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.6
        },
        {
            "sector": "Transport and Communication",
            "size": 11.2
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 30.4
        }],
    "2008": [
        {
            "sector": "Agriculture",
            "size": 4.9
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 17.2
        },
        {
            "sector": "Electricity and Water",
            "size": 1.4
        },
        {
            "sector": "Construction",
            "size": 5.1
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.4
        },
        {
            "sector": "Transport and Communication",
            "size": 11.1
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 28.4
        }],
    "2009": [
        {
            "sector": "Agriculture",
            "size": 4.7
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 16.4
        },
        {
            "sector": "Electricity and Water",
            "size": 1.9
        },
        {
            "sector": "Construction",
            "size": 4.9
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.5
        },
        {
            "sector": "Transport and Communication",
            "size": 10.9
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 27.9
        }],
    "2010": [
        {
            "sector": "Agriculture",
            "size": 4.2
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 16.2
        },
        {
            "sector": "Electricity and Water",
            "size": 2.2
        },
        {
            "sector": "Construction",
            "size": 4.3
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.7
        },
        {
            "sector": "Transport and Communication",
            "size": 10.2
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 28.8
        }],
    "2011": [
        {
            "sector": "Agriculture",
            "size": 4.1
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 14.9
        },
        {
            "sector": "Electricity and Water",
            "size": 2.3
        },
        {
            "sector": "Construction",
            "size": 5
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 17.3
        },
        {
            "sector": "Transport and Communication",
            "size": 10.2
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 27.2
        }],
    "2012": [
        {
            "sector": "Agriculture",
            "size": 3.8
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.3
        },
        {
            "sector": "Manufacturing",
            "size": 14.9
        },
        {
            "sector": "Electricity and Water",
            "size": 2.6
        },
        {
            "sector": "Construction",
            "size": 5.1
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 15.8
        },
        {
            "sector": "Transport and Communication",
            "size": 10.7
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 28
        }],
    "2013": [
        {
            "sector": "Agriculture",
            "size": 3.7
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 14.9
        },
        {
            "sector": "Electricity and Water",
            "size": 2.7
        },
        {
            "sector": "Construction",
            "size": 5.7
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.5
        },
        {
            "sector": "Transport and Communication",
            "size": 10.5
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 26.6
        }],
    "2014": [
        {
            "sector": "Agriculture",
            "size": 3.9
        },
        {
            "sector": "Mining and Quarrying",
            "size": 0.2
        },
        {
            "sector": "Manufacturing",
            "size": 14.5
        },
        {
            "sector": "Electricity and Water",
            "size": 2.7
        },
        {
            "sector": "Construction",
            "size": 5.6
        },
        {
            "sector": "Trade (Wholesale, Retail, Motor)",
            "size": 16.6
        },
        {
            "sector": "Transport and Communication",
            "size": 10.5
        },
        {
            "sector": "Finance, real estate and business services",
            "size": 26.5
        }]
};


/**
 * Create the chart
 */
var currentYear = 1995;
var chart33 = AmCharts.makeChart("chartdiv8", {
    "type": "pie",
    "theme": "light",
    "dataProvider": [],
    "valueField": "size",
    "titleField": "sector",
    "startDuration": 0,
    "innerRadius": 80,
    "pullOutRadius": 20,
    "marginTop": 30,
    "titles": [{
        "text": "South African Economy"
  }],
    "allLabels": [{
        "y": "54%",
        "align": "center",
        "size": 25,
        "bold": true,
        "text": "1995",
        "color": "#555"
  }, {
        "y": "49%",
        "align": "center",
        "size": 15,
        "text": "Year",
        "color": "#555"
  }],
    "listeners": [{
        "event": "init",
        "method": function (e) {
            var chart33 = e.chart;

            function getCurrentData() {
                var data = chartData[currentYear];
                currentYear++;
                if (currentYear > 2014)
                    currentYear = 1995;
                return data;
            }

            function loop() {
                chart33.allLabels[0].text = currentYear;
                var data = getCurrentData();
                chart33.animateData(data, {
                    duration: 1000,
                    complete: function () {
                        setTimeout(loop, 3000);
                    }
                });
            }

            loop();
        }
  }],
    "export": {
        "enabled": true
    }
});
