package com.example.androidtestapp.shared


/**
 * Created by akiyama on 2018/02/27.
 */
class ResponseModel(
    val channel: Channel
)

class Channel(
    val title: String,
    val link: String,
    val description: String,
    val language: String,
    val pubDate: String,
    val items: List<Item>
)

class Item(
    val title: String,
    val link: String,
    val pubDate: String
)