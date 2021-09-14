-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章中的音乐', '2000', '1', 'music', 'blog/music/index', 1, 0, 'C', '0', '0', 'blog:music:list', '#', 'admin', sysdate(), '', null, '文章中的音乐菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章中的音乐查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'blog:music:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章中的音乐新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'blog:music:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章中的音乐修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'blog:music:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章中的音乐删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'blog:music:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章中的音乐导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'blog:music:export',       '#', 'admin', sysdate(), '', null, '');