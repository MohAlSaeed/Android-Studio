package com.example.stockfirebase

class Phone(val id:String, var name:String, var price:Int, var quantity:Int, var available:Boolean, var email:String) {
    constructor():this( "","",0,0,false,""){}
}