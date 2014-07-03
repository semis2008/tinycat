function setSearchKey(key) {
	$("#searchKey").text(key);
}

function search() {
	var searchKey = $("#searchKey").text();
	var searchText = $("#searchText").val();
	var path = $("#contextPath").val();
	$.ajax({
	    url: path + "/lucence/ajax/search",
	    type: "POST",
	    data: {
	        key: searchKey,
	        value: searchText
	    },
	    cache: false,
	    dataType: "json",
	    success: function(date) {
		    var resultList = date.list;
		    var searchHtml = "";
		    $.each(resultList, function(i, val) {
		    	searchHtml += "<tr><td>"+val.title+"</td>" +
		    			"<td>"+val.author_name+"</td>" +
		    			"<td>"+val.publish_time+"</td>" +
		    			"<td>"+val.tags+"</td>" +
		    			"<td>"+val.reply_num+"/"+val.read_num+"</td></tr>";
		    });
		    $("#searchResult").html(searchHtml);
	    }
	});
}
