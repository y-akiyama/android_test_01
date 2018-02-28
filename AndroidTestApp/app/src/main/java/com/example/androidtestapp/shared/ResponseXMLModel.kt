package com.example.androidtestapp.shared

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by akiyama on 2018/02/26.
 */
@Root(name = "rss", strict = false)
class ResponseXMLModel {
    @set:Element(name = "channel")
    @get:Element(name = "channel")
    var channel: ChannelXML? = null

    fun toResponseModel(): ResponseModel? {
        val c = this.channel ?: return null

        val channel = c.let {
            Channel(
                it.title,
                it.link,
                it.description,
                it.language,
                it.pubDate,
                items = c.items.map { Item(it.title, it.link, it.pubDate) }
            )
        }
        return ResponseModel(channel)
    }
}

@Root(name = "channel", strict = false)
class ChannelXML {
    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String = ""

    @set:Element(name = "link")
    @get:Element(name = "link")
    var link: String = ""

    @set:Element(name = "description")
    @get:Element(name = "description")
    var description: String = ""

    @set:Element(name = "language")
    @get:Element(name = "language")
    var language: String = ""

    @set:Element(name = "pubDate")
    @get:Element(name = "pubDate")
    var pubDate: String = ""

    @set:ElementList(name = "item", inline = true)
    @get:ElementList(name = "item", inline = true)
    var items: MutableList<ItemXML> = mutableListOf()
}

@Root(name = "item", strict = false)
class ItemXML {
    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String = ""

    @set:Element(name = "link")
    @get:Element(name = "link")
    var link: String = ""

    @set:Element(name = "pubDate")
    @get:Element(name = "pubDate")
    var pubDate: String = ""
//
//    @set:Element(name = "enclosure")
//    @get:Element(name = "enclosure")
//    var enclosure: String? = null

//    @set:Element(name = "guid")
//    @get:Element(name = "guid")
//    var guid: String? = null
}