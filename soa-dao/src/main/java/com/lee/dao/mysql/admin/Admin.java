package com.lee.dao.mysql.admin;

import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@SuppressWarnings("serial")
@Entity
@Table(name = "adm_admin")
public class Admin implements java.io.Serializable, UserDetails {

	public static final boolean ENABLED = true;
	public static final boolean DISABLED = false;

	@Id
	@Column(length = 40, nullable = false)
	private String username;

	@Column(nullable = false)
	private boolean enabled = ENABLED;

	@Column(length = 40, name = "pwd", nullable = false)
	private String password;

	@Column(length = 40, nullable = false)
	private String name;

	@Column(length = 50)
	private String email;

	@Column(length = 20)
	private String mobile;

	@Column(length = 255)
	private String permitIP;

	@Column(length = 50)
	private String msn;

	@Column(length = 20)
	private String qq;

	@Column(nullable = false)
	private Date createDate;

	@Column(length = 40, nullable = false)
	private String createBy;

	@Column(nullable = true)
	private String channelId;

	@Column
	private String inviterCode;

//	/**
//	 * 用户对应省
//	 */
//
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "adm_role_province",
//			joinColumns = {@JoinColumn(name = "admin_username", referencedColumnName = "username")},
//			inverseJoinColumns = {@JoinColumn(name = "province_id", referencedColumnName = "id")},
//			uniqueConstraints = {@UniqueConstraint(columnNames = {"admin_username", "province_id"})})
//	private List<DepotProvincePo> provincePo;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "adm_admin_role",
			joinColumns = {@JoinColumn(name = "admin_username", referencedColumnName = "username")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
			uniqueConstraints = {@UniqueConstraint(columnNames = {"admin_username", "role_id"})}
	)
	private List<Role> roles;

	//记录登陆用户信息
	@Transient
	private Timestamp lastActiveTime;
	@Transient
	private String loginIP;
	@Transient
	private String lastRequest;

	@Transient
	private String menuJSStr;
	@Transient
	private String permissionPath;

	@Transient
	private List<Menu> menus;

//	@Transient
//	private List<MenuGroupVo> menuGroups;

//	@ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "org_company_admin",
//			joinColumns = {@JoinColumn(name = "admin_id", referencedColumnName = "username")},
//			inverseJoinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")},
//			uniqueConstraints = {@UniqueConstraint(columnNames = {"admin_id", "company_id"})})
//	private Set<CompanyPo> companies;

	/**
	 * 后台账户级别
	 *  1. 平台用户
	 *  2. 省公司用户
	 *	3. 门店用户
	 */
	@Column(columnDefinition = "TINYINT")
	@Convert(converter = AdminLevel.Converter.class)
	private AdminLevel adminLevel;



	public String getNameOrUserName(){
		return StringUtils.isBlank(name) ? username : name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}


	public String getMobile() {
		return mobile;
	}

	public String getPermitIP() {
		return permitIP;
	}

	public Timestamp getLastActiveTime() {
		return lastActiveTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPermitIP(String permitIP) {
		this.permitIP = permitIP;
	}

	public void setLastActiveTime(Timestamp lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}


	public void setLastRequest(String lastRequest) {
		this.lastRequest = lastRequest;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setMenuJSStr(String menuJSStr) {
		this.menuJSStr = menuJSStr;
	}

	public String getPermissionPath() {
		return permissionPath;
	}

	public String getLastRequest() {
		return lastRequest;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getUsername() {
		return username;
	}

	public String getQq() {
		return qq;
	}

	public String getMenuJSStr() {
		return menuJSStr;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getInviterCode() {
		return inviterCode;
	}

	public void setInviterCode(String inviterCode) {
		this.inviterCode = inviterCode;
	}

//	public Set<Integer> getCityIdsOfNormalCompanies(){
//		Set<Integer> cityIds = newHashSet();
//		Set<CompanyPo> companies = getCompanies();
//		if (CollectionUtils.isNotEmpty(companies)) {
//			for (CompanyPo company : companies) {
//				if (company.getStatus() == CommonStatus.ENABLE) {
//					List<CityPo> cities = company.getCities();
//					if (CollectionUtils.isNotEmpty(cities)) {
//						for (CityPo city : cities) {
//							cityIds.add(city.getId());
//						}
//					}
//				}
//			}
//		}
//		return cityIds;
//	}

	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		if (roles != null) {
			for (Role role : roles) {
				List<MenuItem> menuItems = role.getMenuItems();
				for (MenuItem menuItem : menuItems) {
					if (StringUtils.isNotBlank(menuItem.getSymbol())) {
						String[] roleSymbol = menuItem.getSymbol().split("[^\\w_]+");
						for (String sybmol : roleSymbol) {
							auths.add(new AdminAuthority(sybmol));
						}
					}
				}
				List<Resource> resources = role.getResources();
				for (Resource resource : resources) {
//					System.out.println(resource.getSymbol());
					if (StringUtils.isNotBlank(resource.getSymbol())) {
						String[] roleSymbol = resource.getSymbol().split("[^\\w_]+");
						for (String sybmol : roleSymbol) {
							auths.add(new AdminAuthority(sybmol));
						}
					}
				}
			}
			System.out.println(" auth size:" + auths.size());
//			for(GrantedAuthority auth:auths){
//				System.out.println(auth.getAuthority());
//			}
		}
		return auths;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}


	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public void copy(Admin admin) {
		this.enabled = admin.enabled;
		this.name = admin.name;
		this.email = admin.email;
		this.mobile = admin.mobile;
		this.permitIP = admin.permitIP;
		this.msn = admin.msn;
		this.qq = admin.qq;
		this.channelId = admin.channelId;
		this.inviterCode=admin.inviterCode;
	}

	public AdminLevel getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(AdminLevel adminLevel) {
		this.adminLevel = adminLevel;
	}

}
