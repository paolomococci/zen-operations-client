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

import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.html.Main
import com.vaadin.flow.component.html.Paragraph
import com.vaadin.flow.component.html.Section
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.RouteAlias
import local.example.zen.layout.MainLayout

@PageTitle(value = "main view")
@RouteAlias(value = "", layout = MainLayout::class)
@Route(value = "", absolute = false, registerAtStartup = true, layout = MainLayout::class)
class MainView : Main() {

    companion object {
        fun mainView(mainView: MainView) {
            val paragraph = Paragraph()
            val subtitle = H2("access through a RESTful web service")
            paragraph.add("open the navigation bar and click on the links")
            val mainSection = Section(subtitle, paragraph)
            mainView.add(mainSection)
        }
    }
}
