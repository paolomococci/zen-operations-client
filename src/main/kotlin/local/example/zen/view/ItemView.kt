/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.zen.view

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.grid.ColumnTextAlign
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Main
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.function.ValueProvider
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import local.example.zen.layout.MainLayout
import local.example.zen.model.Item
import org.springframework.web.server.ResponseStatusException
import java.net.URISyntaxException

@PageTitle(value = "item view")
@Route(value = "item", layout = MainLayout::class)
class ItemView : Main() {

    private var itemGrid: Grid<Item>? = null
    private var retrieveButton: Button? = null

    fun ItemView() {
        itemGrid = Grid()
        itemGrid!!.addColumn(ValueProvider<Item, Any> {
            item: Item -> item.code
        }).setHeader("code").setSortable(true).textAlign = ColumnTextAlign.START
        itemGrid!!.addColumn(ValueProvider<Item, Any> {
            item: Item -> item.name
        }).setHeader("name").isSortable = true
        itemGrid!!.addColumn(ValueProvider<Item, Any> {
            item: Item -> item.description
        }).setHeader("description").isSortable = false
        itemGrid!!.addColumn(ValueProvider<Item, Any> {
            item: Item -> item.price
        }).setHeader("price").isSortable = true
        retrieveButton = Button(
                "recovers all items",
                VaadinIcon.ARROW_CIRCLE_DOWN_O.create()
        ) { listener: ClickEvent<Button?>? ->
            try {
                itemGrid!!.setItems(
                        // TODO
                )
            } catch (exception: ResponseStatusException) {
                exception.printStackTrace()
            } catch (exception: URISyntaxException) {
                exception.printStackTrace()
            }
        }
        retrieveButton!!.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        this.add(retrieveButton, itemGrid)
    }

    companion object {
        private val RESTFUL_BASE_URI = "http://127.0.0.1:8080/"
    }
}
