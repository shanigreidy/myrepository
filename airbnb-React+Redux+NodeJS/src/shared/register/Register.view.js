import React, { Component } from 'react';
import { fetchUsersData } from '../../actions/home'

class Register extends Component {
    constructor(){
        super();
    }

    setRegisterDetails(){
        let password = this.refs.inputPassword.value;
        let confirmPassword = this.refs.inputConfirmPassword.value;
        let details = {
            userName: this.refs.inputText.value,
            password: password
        }
        debugger
        this.isEquals(password, confirmPassword);
    }

    isEquals(password, confirmPassword){
        if(password === confirmPassword){
            return true;
        }

        return false;
    }

    render() {
        return (  
            <div>     
                <input type="text" placeholder="User Name" ref="inputText" required/>    
                <input type="password" placeholder="Password" ref="inputPassword" required/>    
                <input type="password" placeholder="Confirm Password" ref="inputConfirmPassword" required/> 
                <button onClick={() => this.setRegisterDetails()}> Register </button>   
            </div>
        )

    }
}
function mapStateToProps(state) {
	const { usersData } = state
	return {
  	    usersData
	}
}

function mapDispatchToProps(dispatch) {
	return {
		onFetchUsersData: () => dispatch(fetchUsersData()),
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Register)
