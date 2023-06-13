import json
import random
import rstr
import re

from faker import Faker

faker = Faker()

class LazyDecoder(json.JSONDecoder):
    def decode(self, s, **kwargs):
        regex_replacements = [
            (re.compile(r'([^\\])\\([^\\])'), r'\1\\\\\2'),
            (re.compile(r',(\s*])'), r'\1'),
        ]
        for regex, replacement in regex_replacements:
            s = regex.sub(replacement, s)
        return super().decode(s, **kwargs)


def convert(s):
    new = ""
    for x in s:
        new += x
    return new


def process_gen_generation(key):
    try:
        return getattr(faker, key)()
    except:
        return convert(faker.random_letters())


def process_gen_str_generation(key):
    try:
        return getattr(faker, key)()
    except:
        return convert(faker.random_letters())


def process_gen_int_generation(key):
    try:
        return getattr(faker, key)()
    except:
        return faker.random_int()


def process_gen_num_generation(key):
    try:
        return getattr(faker, key)()
    except:
        return faker.random_number() + 0.58

def process_simple_array(key, arr):
    if "gen" in arr:
        result_list = []
        for i in range(2, random.randrange(5, 8)):
            result_list.append(process_gen_generation(key))
        return result_list
    if "gen_str" in arr:
        result_list = []
        for i in range(2, random.randrange(5, 8)):
            result_list.append(process_gen_generation(key))
        return result_list
    if "gen_int" in arr:
        result_list = []
        for i in range(2, random.randrange(5, 8)):
            result_list.append(process_gen_generation(key))
        return result_list
    if "gen_num" in arr:
        result_list = []
        for i in range(2, random.randrange(5, 8)):
            result_list.append(process_gen_generation(key))
        return result_list


def process_complex_array(template):
    result_list = []
    for i in range(2, random.randrange(5, 8)):
        result_list.append(test_json_process(json.dumps(template)))
    return result_list


def test_json_process(text_json):
    test_real_json = json.loads(text_json, cls=LazyDecoder)
    result_json = {}
    for key in test_real_json:
        if test_real_json[key] == 'gen':
            result_json[key] = process_gen_generation(key)
        elif test_real_json[key] == 'gen_str':
            result_json[key] = process_gen_str_generation(key)
        elif test_real_json[key] == 'gen_int':
            result_json[key] = process_gen_int_generation(key)
        elif test_real_json[key] == 'gen_num':
            result_json[key] = process_gen_num_generation(key)
        elif type(test_real_json[key]) is list:
            json_list = (test_real_json[key])
            if "gen" in json_list or "gen_int" in json_list or "gen_num" in json_list or "gen_str" in json_list:
                result_json[key] = process_simple_array(key, json_list)
            elif type(json_list[0]) is dict:
                result_json[key] = process_complex_array(json_list[0])
        elif type(test_real_json[key]) is dict:
            result_json[key] = test_json_process(json.dumps(test_real_json[key]))
        elif test_real_json[key].startswith('regex'):
            regex = test_real_json[key].split(':')[1]
            result_json[key] = rstr.xeger(r'{}'.format(regex))

    return result_json


import sys

print(test_json_process(sys.argv[1]))