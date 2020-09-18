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

package local.example.zen.layout

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.html.*
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.router.AfterNavigationEvent
import com.vaadin.flow.router.AfterNavigationObserver
import com.vaadin.flow.router.RouterLink
import com.vaadin.flow.server.PWA
import local.example.zen.view.MainView

@Push
@CssImport(value = "style.css")
@PWA(enableInstallPrompt = false, name = "zen-operations-admin", shortName = "operations-admin", startPath = "/main")
@Viewport(value = "width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
class MainLayout : AppLayout(), AfterNavigationObserver {

    private var title: H1? = H1()
    private lateinit var mainView: RouterLink

    fun MainLayout() {
        title = H1("reactive RESTful web service data accessing")
        mainView = RouterLink("main view", MainView::class.java)
        val unorderedList = OrderedList(
                ListItem(mainView)
        )
        val header = Header(DrawerToggle(), title)
        val nav = Nav(unorderedList)
        this.addToNavbar(header)
        addToDrawer(nav)
        this.primarySection = Section.DRAWER
        this.isDrawerOpened = false
    }

    private fun listLinks(): Array<RouterLink?>? {
        return arrayOf(
                mainView
        )
    }

    override fun afterNavigation(afterNavigationEvent: AfterNavigationEvent?) {
        for (link in this.listLinks()!!) {
            if (link!!.highlightCondition.shouldHighlight(link, afterNavigationEvent)) {
                title!!.text = link.text
            }
        }
    }
}
