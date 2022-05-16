import pymysql

con = pymysql.connect(host='localhost', user='root', passwd='ys1234y$YSys', db='sql_intro')
cursor = con.cursor()