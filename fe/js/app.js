(function (window) {
	'use strict';
$(document).ready(function(){
		$.ajax({
			url:'./api/todos',
			dataType:'json',
			type:'get',
	 	 success:function(result) {
		 	reprint(result);
		 }
		});

		$(".new-todo").keydown(function (key) {
	         if(key.keyCode == 13) {
						 if(this.value!="") {
	             insert(this.value);
							 this.value="";
						 }
						 else {
							 alert("Can Not Insert Empty Text!");
						 }
	         }

	     });

		function updateCount(count) {
			$(".todo-count strong").text(count);
		}

		function insert(todo) {
			var data = {}
			data.todo = todo;
			$.ajax({
				url:'./api/todos',
				dataType:'json',
				headers: {
 					'Content-Type': 'application/json'
 				},
				data : JSON.stringify(data),
				type:'POST',
		 	 success:function(result) {
			 var todo_list = $(".todo-list");
			 todo_list.prepend(createNewNode(result));
			 updateCount(parseInt($(".todo-count strong").text())+1);
			 }
			});
		}
		function update(todo,li) {
			$.ajax({
				url:'./api/todos/'+todo.id,
				dataType:'json',
				headers: {
 					'Content-Type': 'application/json'
 				},
				data : JSON.stringify(todo),
				type:'PUT',
		 	 success:function() {
				 if(todo.completed) {
					 updateCount(parseInt($(".todo-count strong").text())-1);
					 li.addClass('completed');
				 }
				 else {
				 	 updateCount(parseInt($(".todo-count strong").text())+1);
					 li.removeClass('completed');
				 }
			 }
			});
		}

		function deleteNode(node) {
			var id = node.id.split("_")[1];
			$.ajax({
				url:'./api/todos/'+id,
				dataType:'json',
				headers: {
 					'Content-Type': 'application/json'
 				},
				type:'DELETE',
		 	 success:function() {
				 node.remove();
				 if(!node.firstChild.firstChild.checked)
				 updateCount(parseInt($(".todo-count strong").text())-1);
			 }
			});
		}
		function reprint(result) {
			var count=0;
			for(var i in result) {
				var node = createNewNode(result[i]);
				$(".todo-list").append(node);
				if(!result[i].completed) count++;
			}
			updateCount(count);
		}

		function createNewNode(node) {
				var li = $('<li/>',{
					id : "todo_"+node.id
				});
				var div = $('<div/>',{
					class: 'view'
				});
				var checkInput = $('<input>',{
					class: 'toggle',
					type: 'checkbox',
				});

				var label = $('<label/>',{
					text: node.todo
				});
				var button = $('<button/>',{
					class:'destroy'
				});
				var editInput = $('<input>',{
					class:'edit',
					value:'Rule the web'
				});
				if(node.completed) {
					li.addClass('completed');
					checkInput.attr("checked",true);
				}
				div.append(checkInput).append(label).append(button);
				li.append(div).append(editInput);
				$(checkInput).on("click", function() {
					var todo = {}
					todo.id=node.id;
					todo.todo=node.todo;
					if(checkInput.is(":checked")) {
						todo.completed=true;
					}
					else {
						todo.completed=false;
					}
					update(todo,li);


				});
				return li;
			}


			$(".todo-list").on("click",".destroy", function() {
				deleteNode(this.parentNode.parentNode);
			});

			$(".filters").on("click","a",function(){
				$(this).parent().siblings().children().removeClass('selected');
				$(this).addClass('selected');
				filtering(this);
			});

			function filtering(node) {


				$(".todo-list").children().each(function(index) {
					if($(node).text()=="All") {
						$(this).removeClass('hidden');
					}
					else if($(node).text()=="Active") {
						if($(this).hasClass("completed")) {
							$(this).addClass('hidden');
						}
						else {
							$(this).removeClass('hidden');
						}
					}
					else if($(node).text()=="Completed") {
						if($(this).hasClass("completed")) {
							$(this).removeClass('hidden');
						}
						else {
							$(this).addClass('hidden');
						}
					}
				});

			}


			$(".clear-completed").on("click",function() {
				$(".todo-list").children().each(function(index) {
						if($(this).hasClass("completed")) {
							deleteNode(this);
						}
				});
		});

	// Your starting point. Enjoy the ride!
});
})(window);
