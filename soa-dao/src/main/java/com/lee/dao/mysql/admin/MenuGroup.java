package com.lee.dao.mysql.admin;

import javax.persistence.*;
import java.util.List;

/**
 * 菜单集合
 */
@SuppressWarnings("serial") @Entity @Table(name = "adm_menugroup") public class MenuGroup
    implements java.io.Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    /**
     * 名称
     */
    @Column(length = 50, nullable = false, unique = true) private String name;

    /**
     * 描述
     */
    @Column(length = 255) private String description;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "adm_menugroup_menu",
        joinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "menugroup_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "menugroup_id"})})
    private List<MainMenu> menus;

    /**
     * 排序
     */
    @Column private Integer idx;

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MenuGroup menuGroup = (MenuGroup) o;

        return !(id != null ? !id.equals(menuGroup.id) : menuGroup.id != null);

    }

    @Override public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * {@linkplain MenuGroup#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@linkplain MenuGroup#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@linkplain MenuGroup#name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@linkplain MenuGroup#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@linkplain MenuGroup#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@linkplain MenuGroup#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@linkplain MenuGroup#menus}
     */
    public List<MainMenu> getMenus() {
        return menus;
    }

    /**
     * {@linkplain MenuGroup#menus}
     */
    public void setMenus(List<MainMenu> menus) {
        this.menus = menus;
    }

    /**
     * {@linkplain MenuGroup#idx}
     */
    public Integer getIdx() {
        return idx;
    }

    /**
     * {@linkplain MenuGroup#idx}
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
