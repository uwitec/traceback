/**
 * @class Ext.ux.tree.TriStateTreePanel
 * @extends Ext.tree.Panel
 * @requires Ext.selection.TreeModel
 * @version 0.1
 * @author ����
 * @contact 372289475@qq.com
 * 
 * 
 * <h3>Features:</h3>
 * 
 * <ol>
 * <li> Extends Ext.tree.Panel </li>
 * <li> If a parent node is checked / unchecked, all its child nodes are
 * automatically checked / unchecked too. </li>
 * 
 * 
 * <li> If only some children of a node are selected, its checkbox remains
 * checked, but with a third visual state, using a darkened background.
 * <p class="small">
 * A single file (tricheckboxes.gif) defines all the three images.
 * </p>
 * </li>
 * 
 * <h3>How to use:</h3>
 * 
 * <pre>
 * .x-panel-body .x-tree-checkbox {
 *         background-image:url(tricheckbox.gif) !important;
 *         }
 * 
 * .x-panel-body .x-tree-checkbox-checked-indeterminate .x-tree-checkbox {
 *         background-position: 0 -26px;
 *         }
 * </pre>
 * 
 * </ol>
 * 
 */

Ext.define('Ext.ux.tree.TriStateTreePanel', {
	extend : 'Ext.tree.Panel',
	rootVisible : false,
	useArrows : true,
	frame : false,
	header : false,
	indeterminateCls : 'x-tree-checkbox-indeterminate',
	width : 350,
	height : 400,

	listeners : {
		checkchange : function(node, checked) {
			var me = this;

			// �ı�ýڵ�������ӽڵ�״̬������disabled״̬�ĵ㲻�����ı䣻
			// �ݹ鷵��ԭ�򣺽ڵ�ѡ��Ϊ1��δ��ѡ��Ϊ-1����ѡ״̬Ϊ0
			// Ҷ�ӽڵ㲻���ڰ�ѡ״̬
			// ��Ҷ�ӽڵ㵱����״̬��Ҷ�ӽڵ�״̬�Ͳ���ʱ��checked����Ϊfalse������cls����
			me.setChildrenCheckedStatus(node, checked);
			me.updateParentCheckedStatus(node, checked);
		}
	},

	setChildrenCheckedStatus : function(node, check) {
		var me = this;
		var len = node.childNodes.length;
		if (len <= 0 || node.get('disabled') === true) {
			if (node.get('disabled') === false)
				node.set('checked', check);
			return node.get('checked') ? 1 : -1;
		} else {
			var sumcount = 0;
			for (var index = 0; index < len; index++) {
				var subnode = node.childNodes[index];

				subnode.set('checked', check)

				sumcount = sumcount
						+ me.setChildrenCheckedStatus(subnode, check);
			}
			if (len === Math.abs(sumcount)) {
				me.unsetThirdState(node);
				node.set('checked', check);
				return node.get('checked') ? 1 : -1;
			} else {
				me.setThirdState(node);
				return 0;
			}

		}
	},

	updateParentCheckedStatus : function(node, check) {
		var me = this;
		var nodeparent = node.parentNode;
		// if (!nodeparent || nodeparent===panel.getRootNode()) { return; }
		if (!nodeparent) {
			return;
		} else {
			var len = nodeparent.childNodes.length;
			var sumcount = 0;
			for (var index = 0; index < len; index++) {
				var subnode = nodeparent.childNodes[index];
				if (me.isThirdState(subnode)) {
					sumcount = sumcount + 0;
				} else {
					if (subnode.get('checked')) {
						sumcount = sumcount + 1;
					} else {
						sumcount = sumcount - 1;
					}
				}
			}
			if (len === Math.abs(sumcount)) {
				me.unsetThirdState(nodeparent);
				nodeparent.set('checked', check);
			} else {
				me.setThirdState(nodeparent);
			}
			me.updateParentCheckedStatus(nodeparent, check);
		}
	},

	isThirdState : function(node) {
		return node.get('cls') == this.indeterminateCls;

	},

	setThirdState : function(node) {
		node.set('cls', this.indeterminateCls);
		node.set('checked', true);
	},

	unsetThirdState : function(node) {
		node.set('cls', '');
	},

	getLeafIdSelections : function() {
		var me = this;

		try {
			var tree_selections = me.getView().getChecked(), result = [];

			Ext.Array.each(tree_selections, function(rec) {
				if (rec.get('id')) {
					result.push(rec.get('id'));
				}
			});

			return result;
		} catch (e) {
			debug('[error in accessPanel getSelections]');
			debug(e);
		}
	},

	setSelections : function(codes) {
		var ids = codes.split(",");
		var me = this;

		if (ids[0] && ids[0]['id']) {
			ids = Ext.Array.pluck(ids, 'id');
		}

		me.getRootNode().cascadeBy(
				function() {
					var currNode = this;
					if (currNode.get('leaf')) {
						currNode.set('checked',
								ids.indexOf(currNode.get('id')) > -1);
						me.updateParentCheckedStatus(currNode, ids
								.indexOf(currNode.get('id')) > -1);
					}
				});
	}
});