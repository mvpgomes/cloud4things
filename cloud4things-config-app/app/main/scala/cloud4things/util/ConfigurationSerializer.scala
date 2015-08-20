package main.scala.cloud4things.util

import main.scala.cloud4things.configuration.Configuration
import play.api.libs.json._


object ConfigurationSerializer {

  /*
   Serialize all defined configuration to a JSON object that contains the configuration names.
   */
  def serializeAllConfigurations(configurationNames: Iterable[String]) = {
    val configurationNamesToJson = Json.obj(
    "smart_places" ->
        configurationNames.map { configuration =>
          Json.obj("name" -> configuration);
        }
    )
    configurationNamesToJson
  }

  /*
    Serialize a smart place configuration to JSON object according the following format:

     {
        "name": "smart place name",
        "endpoint": "smart place endpoint",
        "readers": [
                    { "reader_name": "name" }
                   ],
        "events": [
                    { "event_name": "name", "associated_reader": "reader_name"}
                  ]
     }

     */
  def serializeConfiguration(configuration: Option[Configuration]) =  configuration match {
    case Some(conf) => {
      val configurationToJson = Json.obj(
        "name" -> conf.id,
        "endpoint" -> conf.endpoint,
        "readers" -> conf.getAllReaders.map { reader =>
          Json.obj("reader_name" -> reader.readerName,
                   "reader_description" -> reader.readerDescription)
        },
        "events" -> conf.getAllEvents.map { event =>
          Json.obj("event_name" -> event.eventName,
            "associated_reader" -> event.associatedReader
          )
        }
      )
      configurationToJson
    }
    case None => Json.obj("data" -> "ERROR: Configuration does not exists.")
  }
}
