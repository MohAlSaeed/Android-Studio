package com.example.stockfirebase

class Store(val id:String, var name:String, var desc:String, var rad:Float,var lat:Double ,var lon:Double, var address: String) {
    constructor():this("","","",100F, 20.0, 20.0, ""){}
}