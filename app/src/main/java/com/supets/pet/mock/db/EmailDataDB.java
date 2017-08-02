package com.supets.pet.mock.db;

import android.support.annotation.NonNull;

import com.supets.pet.greendao.SessionFactory;
import com.supets.pet.mock.bean.EmailData;
import com.supets.pet.mock.config.MockConfig;

import java.util.List;

public class EmailDataDB extends SessionFactory {

    public static List<EmailData> queryAll() {
        return getDbSession().getEmailDataDao().queryBuilder().list();
    }

    public static List<EmailData> queryEmailDataById(String id) {
        return getDbSession().getEmailDataDao().queryRaw("where _id = ?  ", id);
    }

    public static void insertEmailData(EmailData status) {
        getDbSession().getEmailDataDao().insert(status);
    }

    public static void updateEmailData(EmailData status) {
        getDbSession().getEmailDataDao().update(status);
    }

    public static void deleteEmailData(EmailData status) {
        getDbSession().getEmailDataDao().delete(status);
    }

    public static void deleteAll() {
        getDbSession().getEmailDataDao().deleteAll();
    }

    @NonNull
    public static String[] getEmailList() {
        String[] list;List<EmailData> data = EmailDataDB.queryAll();
        if (data != null && data.size() > 0) {
            list = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                list[i] = data.get(i).getName()+"<"+data.get(i).getEmail()+">";
            }
        } else {
            list = new String[1];
            list[0] ="发件人<" + MockConfig.getEmailName()+">";
        }
        return list;
    }

}
