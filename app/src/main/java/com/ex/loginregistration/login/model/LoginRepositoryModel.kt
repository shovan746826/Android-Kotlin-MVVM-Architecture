package com.ex.loginregistration.login.model

data class LoginRepositoryModel(
    var is_success: Boolean = false,
    var user_first_name: String? = null,
    var user_last_name: String? = null,
    var user_email: String? = null,
    var user_number: String? = null,
    var user_reg_id: String? = null,
    var is_parent_customer: Boolean = false,
    var token: String? = null,
    var message: String? = null
)
