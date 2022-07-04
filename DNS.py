# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import dns
import dns.resolver
import pandas as pd

df = pd.read_excel("~/Desktop/Domain.xlsx")
names = df['names'].values.tolist()
count = 0
lst = []
lst.append(names)
Test_data = {}
i = 0
length = len(lst)
while i < length:
    domain = lst[i]
    result = dns.resolver.resolve("mqtt.dev.tv.yo-digital.com")
    for ival in result:
        print('IP', ival.to_text())
    i+=1
