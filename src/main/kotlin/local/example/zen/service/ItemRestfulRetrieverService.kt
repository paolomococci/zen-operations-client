/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package local.example.zen.service

import local.example.zen.model.Item
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.client.Traverson
import java.net.URI
import java.util.*

class ItemRestfulRetrieverService {

    fun getListOfItems(uri: URI?): ArrayList<Item?> {
        val traverson = Traverson(uri!!, MediaTypes.HAL_JSON)
        val traversalBuilder = traverson.follow("items")
        val parameterizedTypeReference: ParameterizedTypeReference<CollectionModel<Item>> =
                ParameterizedTypeReference.forType(Item::class.java)
        var collectionModelOfItems: CollectionModel<Item>?
        collectionModelOfItems = traversalBuilder.toObject(parameterizedTypeReference)
        val collectionOfItems = collectionModelOfItems!!.content
        return ArrayList<Item?>(collectionOfItems)
    }
}
