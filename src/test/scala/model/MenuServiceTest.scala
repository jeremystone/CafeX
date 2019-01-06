package model

import billing.MenuService
import model.MenuItem.{CheeseSandwich, Coffee, Cola, SteakSandwich}
import org.scalatest.{MustMatchers, WordSpec}

/**
  * Tests for the [[MenuService]]
  */
class MenuServiceTest extends WordSpec with MustMatchers {
  val menuService = new MenuService

  "MenuService" when {
    "an item on the menu is retrieved by name" must {
      "return that item" in {
        menuService.menuItemByName("Cola") mustBe Some(Cola)
        menuService.menuItemByName("Coffee") mustBe Some(Coffee)
        menuService.menuItemByName("Cheese Sandwich") mustBe Some(CheeseSandwich)
        menuService.menuItemByName("Steak Sandwich") mustBe Some(SteakSandwich)
      }
    }

    "an item not on the menu is retrieved by name" must {
      "return None" in {
        menuService.menuItemByName("Deep-fried Mars Bar") mustBe None
      }
    }
  }

}
