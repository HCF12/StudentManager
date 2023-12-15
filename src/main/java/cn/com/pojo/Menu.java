package cn.com.pojo;

import java.util.List;

public class Menu {

    private String menuId;

    private String menuName;

    private String menuCode;

    private String menuAction;

    private List<Menu> menuList;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public List<Menu> getMenu() {
        return menuList;
    }

    public void setMenu(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
