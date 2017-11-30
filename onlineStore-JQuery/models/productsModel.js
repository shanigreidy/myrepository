module.exports = function(){
    var fs = require('fs');
    var jsonProducts;
    var categoriesArr = [];
   
    fs.readFile("./data/products.json", function (err, content) 
        {

            if (err) 
            {
                // error(response);
                console.log("error");
            }

            jsonProducts = JSON.parse(content);
            initialProducts();
        });

    function error(response) 
    {
        response.writeHead(404, { "Content-Type": "text/html" });
        response.write("Not Found");
        response.end();
    }

    function initialProducts()
    {
        for(var category in jsonProducts)
        {
            var categoryObj = jsonProducts[category];

            for(var subCategory in categoryObj)
            {
                var subCategoryArr =  categoryObj[subCategory];

                for(var i=0; i<subCategoryArr.length; i++)
                {
                   subCategoryArr[i].url = category +"/"+subCategory+"/"+ subCategoryArr[i].name;
                   subCategoryArr[i].quantity = 1;
                }
            }
        }
    }

    function getCategories()
    {
        var categoriesArr = [];
        var subCategoriesArr = [];
        var productsObj = {};

        for(var category in jsonProducts)
        {
            categoriesArr.push(category);
        }

        for(var i=0; i<categoriesArr.length; i++)
        {
            var categoryObj = jsonProducts[categoriesArr[i]];

            for(var subCategory in categoryObj)
            {
                subCategoriesArr.push(subCategory);
            }

            productsObj[categoriesArr[i]] = subCategoriesArr;
        }

       return productsObj;
    }

    function getRandomProducts()
    {  
        var allProducts = [];
        var randomProducts = [];
        var oneProduct;

        for(var category in jsonProducts)
        {
            var categoryObj = jsonProducts[category];

            for(var subCategory in categoryObj)
            {
                allProducts.push(categoryObj[subCategory]);
            }
        }

        for(var i=0; i<9; i++)
        {
            oneProduct = allProducts[i];
            randomProducts.push(oneProduct[Math.floor(Math.random()*oneProduct.length)]);
        }

        return randomProducts;
    }

    function getProductsByCatAndSubCat(category, subCategory)
    {
        var categoryArr = jsonProducts[category];
        var productsArr = categoryArr[subCategory];

        return productsArr;
    }

    function getSelectedProduct(category, subCategory, productName)
    {
        var categoryArr = jsonProducts[category];
        var productsArr = categoryArr[subCategory];
        
        for(var i=0; i<productsArr.length; i++)
        {
            if(productsArr[i].name === productName)
            {
                return productsArr[i];
            }
        }
    }

    return{
        getCategories: getCategories,
        getRandomProducts: getRandomProducts,
        getProductsByCatAndSubCat: getProductsByCatAndSubCat,
        getSelectedProduct: getSelectedProduct,
    };
}();