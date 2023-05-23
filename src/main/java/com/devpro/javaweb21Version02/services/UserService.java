package com.devpro.javaweb21Version02.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devpro.javaweb21Version02.dao.RoleRepository;
import com.devpro.javaweb21Version02.dao.UserRepository;
import com.devpro.javaweb21Version02.model.Role;
import com.devpro.javaweb21Version02.model.SearchModel;
import com.devpro.javaweb21Version02.model.User;

@Service
public class UserService extends BaseService<User> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	protected Class<User> clazz() {
		return User.class;
	}

	public User loadUserByUsername(String userName) {
		String sql = "select * from tbl_users u where u.username = '" + userName + "' and status = 1";
		return this.getEntityByNativeSQL(sql);
	}

//	@Transactional
//	public User add(User p, Role r) throws IllegalStateException, IOException {
//
//		// tạo seo: bổ sung thêm thời gian tính bằng miliseconds để tránh trùng seo
////		p.setSeo(new Slugify().slugify(p.getTitle() + "-" + System.currentTimeMillis()));
//
//		// lưu vào database
//		p.addRoles(r);
//		r.addUsers(p);
//		return super.saveOrUpdate(p);
//
//	}

	@Transactional
	public User update(User p, Role r) throws IllegalStateException, IOException {

		// tạo seo: bổ sung thêm thời gian tính bằng miliseconds để tránh trùng seo
//		p.setSeo(new Slugify().slugify(p.getTitle() + "-" + System.currentTimeMillis()));
		p.deleteRole(r);
		p.addRoles(r);
		// lưu vào database
		return super.saveOrUpdate(p);
	}

	public PagerData<User> search(SearchModel searchModel) {
		// khởi tạo câu lệnh
		String sql = "SELECT tbl_users.id,tbl_users.username,tbl_users.password, tbl_users.fullname,"
				+ "tbl_users.phone,tbl_users.created_date,tbl_users.updated_date,tbl_users.updated_by,"
				+ "tbl_users.created_by,tbl_users.status FROM tbl_users,tbl_users_roles "
				+ "WHERE 1=1 and tbl_users.id=tbl_users_roles.user_id and tbl_users_roles.role_id = 17";

		if (searchModel != null) {

			if (searchModel.getKeyoption() != null) {
				if (searchModel.getKeyoption() == "") {
					sql += "";

				}
				if (searchModel.getKeyoption() != "") {
					sql += "  and tbl_users_roles.role_id = '" + searchModel.getKeyoption() + "'";
				}
			}

			// tìm kiếm theo username,phone
			if (!StringUtils.isEmpty(searchModel.getKeyword())) {
				sql += " and (tbl_users.username like '%" + searchModel.getKeyword() + "%'"
						+ " or tbl_users.fullname like '%" + searchModel.getKeyword() + "%'"
						+ " or tbl_users.phone like '%" + searchModel.getKeyword() + "%')";
			}
		}

		// chi lay san pham chua xoa
//						sql += " and p.status=1 ";

		return getEntitiesByNativeSQL(sql, searchModel.getPage());
	}

	public PagerData<User> searchEmployee(SearchModel searchModel) {
		// khởi tạo câu lệnh
		String sql = "SELECT tbl_users.id,tbl_users.username,tbl_users.password, "
				+ "tbl_users.fullname,tbl_users.phone,tbl_users.created_date,"
				+ "tbl_users.updated_date,tbl_users.updated_by,tbl_users.created_by,"
				+ "tbl_users.status FROM tbl_users,tbl_users_roles "
				+ "WHERE 1=1 and tbl_users.id=tbl_users_roles.user_id " + "and tbl_users_roles.role_id = 18";

		if (searchModel != null) {

			if (searchModel.getKeyoption() != null) {
				if (searchModel.getKeyoption() == "") {
					sql += "";

				}
				if (searchModel.getKeyoption() != "") {
					sql += "  and tbl_users_roles.role_id = '" + searchModel.getKeyoption() + "'";
				}
			}

			if (!StringUtils.isEmpty(searchModel.getKeyword())) {
				sql += " and (tbl_users.username like '%" + searchModel.getKeyword() + "%'"
						+ " or tbl_users.fullname like '%" + searchModel.getKeyword() + "%'"
						+ " or tbl_users.phone like '%" + searchModel.getKeyword() + "%')";
			}
		}

		// chi lay san pham chua xoa
//						sql += " and p.status=1 ";

		return getEntitiesByNativeSQL(sql, searchModel.getPage());
	}

	public PagerData<User> searchAdmin(SearchModel searchModel) {
		// khởi tạo câu lệnh
		String sql = "SELECT tbl_users.id,tbl_users.username,tbl_users.password, tbl_users.fullname,"
				+ "tbl_users.phone,tbl_users.created_date,tbl_users.updated_date,tbl_users.updated_by,"
				+ "tbl_users.created_by,tbl_users.status FROM tbl_users,tbl_users_roles "
				+ "WHERE 1=1 and tbl_users.id=tbl_users_roles.user_id and tbl_users_roles.role_id = 16";

		if (searchModel != null) {

			if (searchModel.getKeyoption() != null) {
				if (searchModel.getKeyoption() == "") {
					sql += "";

				}
				if (searchModel.getKeyoption() != "") {
					sql += "  and tbl_users_roles.role_id = '" + searchModel.getKeyoption() + "'";
				}
			}

			// tìm kiếm theo username,phone
			if (!StringUtils.isEmpty(searchModel.getKeyword())) {
				sql += " and (tbl_users.username like '%" + searchModel.getKeyword() + "%'"
						+ " or tbl_users.fullname like '%" + searchModel.getKeyword() + "%'"
						+ " or tbl_users.phone like '%" + searchModel.getKeyword() + "%')";
			}
		}

		// chi lay san pham chua xoa
//						sql += " and p.status=1 ";

		return getEntitiesByNativeSQL(sql, searchModel.getPage());
	}

	public User saveGuest(User entity) {
		String pass = entity.getPassword();
		entity.setPassword(new BCryptPasswordEncoder(4).encode(pass));
		Role roleUser = roleRepository.findByName("GUEST");
		entity.addRoles(roleUser);
		return userRepository.save(entity);
	}

	public User saveEmployee(User entity) {
		String pass = entity.getPassword();
		entity.setPassword(new BCryptPasswordEncoder(4).encode(pass));
		Role roleUser = roleRepository.findByName("EMPLOYEE");
		entity.addRoles(roleUser);
		return userRepository.save(entity);
	}

	public User saveAdmin(User entity) {
		String pass = entity.getPassword();
		entity.setPassword(new BCryptPasswordEncoder(4).encode(pass));
		Role roleUser = roleRepository.findByName("ADMIN");
		entity.addRoles(roleUser);
		return userRepository.save(entity);
	}

	public User updateEmployee(User entity) {
		Role roleUser = roleRepository.findByName("EMPLOYEE");
		entity.addRoles(roleUser);
		return userRepository.save(entity);
	}

	public User updateAdmin(User entity) {
		Role roleUser = roleRepository.findByName("ADMIN");
		entity.addRoles(roleUser);
		return userRepository.save(entity);
	}

	public User save(User entity) {
		String pass = entity.getPassword();
		entity.setPassword(new BCryptPasswordEncoder(4).encode(pass));
		// Role roleEmployee = roleRepository.findByName("EMPLOYEE");
		// entity.addRoles(roleEmployee);
		return userRepository.save(entity);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}