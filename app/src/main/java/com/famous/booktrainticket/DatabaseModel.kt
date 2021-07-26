package com.famous.booktrainticket

class DatabaseModel(
    var userId: String, var fullName: String,
                         var destination:String,
                         var source: String,
//                         var date: Int? = null
                         )
{
    constructor() : this("", "", "", "") {

    }
}
