-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章标签', '2000', '1', 'article_tag', 'blog/article_tag/index', 1, 0, 'C', '0', '0', 'blog:article_tag:list', '#', 'admin', sysdate(), '', null, '文章标签菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章标签查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'blog:article_tag:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章标签新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'blog:article_tag:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章标签修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'blog:article_tag:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章标签删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'blog:article_tag:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章标签导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'blog:article_tag:export',       '#', 'admin', sysdate(), '', null, '');