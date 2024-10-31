package com.li.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AccountGenerator {
    private static Set<String> existingAccounts = new HashSet<>();

    public static String generateUniqueAccount() {
        String account;
        do {
            account = generateAccount();
        } while (existingAccounts.contains(account));

        existingAccounts.add(account);
        return account;
    }

    private static String generateAccount() {
        // 生成一个 UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 取前 10 位
        String account = uuid.substring(0, 10);

        // 确保首位数字在 [1-9] 范围内
        char firstChar = account.charAt(0);
        if (firstChar == '0') {
            // 如果首位是 '0'，替换为 '1'，保证有效性
            account = '1' + account.substring(1);
        } else {
            // 如果首位是非数字，替换为 '1'，保证有效性
            if (!Character.isDigit(firstChar)) {
                account = '1' + account.substring(1);
            }
        }

        return account;
    }
}
