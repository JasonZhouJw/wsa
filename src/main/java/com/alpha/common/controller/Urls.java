package com.alpha.common.controller;

/**
 * Created by jzhou237 on 2016-12-06.
 */
public class Urls {

    public static final String SEPARATOR = "/";

    public static final String ROOT = SEPARATOR;

    public static final String HOME = ROOT + "home";

    public static final String LOGIN = ROOT + "login";

    public static final String LOGOUT = ROOT + "logout";

    public static final String ACCOUNT = ROOT + "account";
    public static final String ACCOUNT_TO_CREATE = ACCOUNT + SEPARATOR + "toCreate";
    public static final String ACCOUNT_CREATE = ACCOUNT + SEPARATOR + "create";
    public static final String ACCOUNT_INDEX = ACCOUNT + SEPARATOR + "index";
    public static final String ACCOUNT_TO_UPDATE = ACCOUNT + SEPARATOR + "toUpdate";
    public static final String ACCOUNT_UPDATE = ACCOUNT + SEPARATOR + "update";
    public static final String ACCOUNT_TO_CHANGE_PASSWORD = ACCOUNT + SEPARATOR + "toChangePassword";
    public static final String ACCOUNT_CHANGE_PASSWORD = ACCOUNT + SEPARATOR + "changePassword";

    public static final String VERIFY_RESULT = ROOT + "verifyResult";
    public static final String VERIFY_RESULT_INDEX = VERIFY_RESULT + SEPARATOR + "index";
    public static final String VERIFY_RESULT_SEARCH = VERIFY_RESULT + SEPARATOR + "search";
    public static final String VERIFY_RESULT_TO_UPDATE = VERIFY_RESULT + SEPARATOR + "toUpdate";
    public static final String VERIFY_RESULT_UPDATE = VERIFY_RESULT + SEPARATOR + "update";

    public static final String TEST_CASE = ROOT + "testCase";
    public static final String TEST_CASE_INDEX = TEST_CASE + SEPARATOR + "index";
    public static final String TEST_CASE_TO_CREATE = TEST_CASE + SEPARATOR + "create";
    public static final String TEST_CASE_CREATE = TEST_CASE + SEPARATOR + "create";
    public static final String TEST_CASE_TO_UPDATE = TEST_CASE + SEPARATOR + "toUpdate";
    public static final String TEST_CASE_UPDATE = TEST_CASE + SEPARATOR + "update";
    public static final String TEST_CASE_SEARCH = TEST_CASE + SEPARATOR + "search";
    public static final String TEST_CASE_EXECUTE = TEST_CASE + SEPARATOR + "execute";


    public static final String CASE_GROUP = TEST_CASE + SEPARATOR + "caseGroup";
    public static final String CASE_GROUP_INDEX = CASE_GROUP + SEPARATOR + "index";
    public static final String CASE_GROUP_TO_UPDATE = CASE_GROUP + SEPARATOR + "toUpdate";
    public static final String CASE_GROUP_UPDATE = CASE_GROUP + SEPARATOR + "update";
    public static final String CASE_GROUP_TO_CREATE = CASE_GROUP + SEPARATOR + "toCreate";
    public static final String CASE_GROUP_CREATE = CASE_GROUP + SEPARATOR + "create";

    public static final String SERVICE_INFO = ROOT + "serviceInfo";
    public static final String SERVICE_INFO_INDEX = SERVICE_INFO + SEPARATOR + "index";
    public static final String SERVICE_INFO_SEARCH = SERVICE_INFO + SEPARATOR + "search";
    public static final String SERVICE_INFO_TO_CREATE = SERVICE_INFO + SEPARATOR + "toCreate";
    public static final String SERVICE_INFO_CREATE = SERVICE_INFO + SEPARATOR + "create";
    public static final String SERVICE_INFO_CREATE_ASSEMBLE = SERVICE_INFO + SEPARATOR + "createAndAssemble";
    public static final String SERVICE_INFO_TO_UPDATE = SERVICE_INFO + SEPARATOR + "toUpdate";
    public static final String SERVICE_INFO_UPDATE = SERVICE_INFO + SEPARATOR + "update";
    public static final String SERVICE_INFO_ASSEMBLE = SERVICE_INFO + SEPARATOR + "assemble";

}
