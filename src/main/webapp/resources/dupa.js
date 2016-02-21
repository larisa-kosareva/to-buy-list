function addNewProductList() {
	var url = root + "create-new-list";
	$.get(url, function(result) {
		var html = '<div class="col-md-4" id="productList' + result.listId + '">'
			+ '<h2>' + result.date + '</h2>'
			+ '<input type="checkbox" value="done" onclick="markAsDone('
			+ result.listId + ');">done' + '</div>';
		$(html).insertBefore("#add-new-product-div");
	});
}

function markAsDone(productListId) {
	var url = root + "done?productListId=" + productListId;
	$.get(url, function(data) {
		$('#productList' + productListId).remove();
	});
}

$(document).ready(function() {
	$("input.productListInput").keypress(function(e) {
		if (e.which == 13) {
			var listId = $(this).attr("data-listId");
			var productName = $(this).val();
			var url = root + "add-product?productListId=" + listId + "&productName=" + productName;
			$.get(url, function(data) {
				
				var html = '<p>' + productName+ '</p>';
				$(html).insertBefore('#productListInput' + listId);
			});
		}
	});
});