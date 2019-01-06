package billing

import model.MenuItem

/**
  * Represents the Menu.
  */
class MenuService {
  /**
    * Finds the named item on the menu
    * @param name the item name
    * @return the item if it is present on the menu or else None
    */
  def menuItemByName(name: String): Option[MenuItem] =
    MenuItem.parseByName(name)
}
