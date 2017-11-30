module.exports = function(){
    var cartData = [];
    var productsDataObj={
                           products: [],
                           allProductsTotalSum:0
                        };


    function addProductToCart(product)
    {
        if(isProductExistsInCart(product.name))
        {
           addProductQuantity(product.name);
        }
        else
        {
            cartData.push(product);
        }
    }

    function getCartData()
    {
        return cartData;
    }

    function isProductExistsInCart(productName)
    {
        for(var i=0; i<cartData.length; i++)
        {
            if(cartData[i].name === productName)
            {
                return true;
            }
        }

        return false;
    }

    function addProductQuantity(productName)
    {
        for(var i=0; i<cartData.length; i++)
        {
            if(cartData[i].name === productName)
            {
                var qty = parseInt(cartData[i].quantity);
                qty++;
                cartData[i].quantity = qty;
                break;
            }
        }
    }

    function changeProductQuantity(product)
    {
        for(var i=0; i<cartData.length; i++)
        {
            if(cartData[i].name === product.name)
            {
                var qty = parseInt(cartData[i].quantity);
                var newQty = parseInt(product.newQuantity);

                if(qty > newQty)
                {
                    qty--;
                }
                else if(qty < newQty)
                {
                    qty++;
                }

                cartData[i].quantity = qty;
                break;
            }
        }
    }

    function deleteProduct(productName)
    {
        for(var i=0; i<cartData.length; i++)
        {
            if(cartData[i].name === productName)
            {
                cartData.splice(i, 1);
                break;
            }
        }
    }

    function createCart()
    {
        productsDataObj.products.length = 0;
        
        for(var i=0; i<cartData.length; i++)
        {
            var name = cartData[i].name;
            var quantity = parseInt(cartData[i].quantity);
            var price = parseFloat(cartData[i].price);
            var totalProductPrice = price * quantity;
            productsDataObj.allProductsTotalSum += totalProductPrice;
            
            productsDataObj.products.push({name: name, quantity: quantity, price: price, totalProductPrice: totalProductPrice});
        }

        return productsDataObj;
    }

    function getCartProductByName(productName)
    {
        for(var i=0; i<cartData.length; i++)
        {
            if(cartData[i].name === productName)
            {
               return cartData[i];
            }
        }
    }

    function getNumberOfProductsInCart()
    {
        var numberOfProductsInCart = 0;
        
        for(var i=0; i<cartData.length; i++)
        {
           numberOfProductsInCart += parseInt(cartData[i].quantity);
        }

        return numberOfProductsInCart;
    }

    return{
        addProductToCart: addProductToCart,
        getCartData: getCartData,
        deleteProduct: deleteProduct,
        createCart: createCart,
        getCartProductByName: getCartProductByName,
        getNumberOfProductsInCart: getNumberOfProductsInCart,
        changeProductQuantity: changeProductQuantity
    };

}();