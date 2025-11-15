import pandas as pd

df = pd.read_parquet("part-00000-1354d8fc-6366-4196-b536-987543689308.c000.snappy.parquet")
print(df.to_string())

