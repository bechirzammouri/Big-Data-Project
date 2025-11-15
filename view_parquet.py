import pandas as pd
import sys
import os

def view_parquet_file(file_path):
    """
    View contents of a parquet file using pandas
    """
    try:
        if not os.path.exists(file_path):
            print(f"❌ Error: File '{file_path}' not found")
            return
        
        if not file_path.endswith('.parquet'):
            print(f"❌ Error: File '{file_path}' is not a parquet file")
            return
        
        print(f"📊 Reading parquet file: {file_path}")
        print("=" * 80)
        
        df = pd.read_parquet(file_path)
        
        print(f"📈 Total records: {len(df)}")
        print(f"📋 Columns: {list(df.columns)}")
        print("=" * 80)
        print("📄 Data:")
        print(df.to_string())
        
    except Exception as e:
        print(f"❌ Error reading parquet file: {e}")

def main():
    if len(sys.argv) != 2:
        print("Usage: python3 view_parquet.py <parquet_file_path>")
        print("\nExample:")
        print("  python3 view_parquet.py part-00000-1354d8fc-6366-4196-b536-987543689308.c000.snappy.parquet")
        print("  python3 view_parquet.py /path/to/your/file.parquet")
        sys.exit(1)
    
    file_path = sys.argv[1]
    view_parquet_file(file_path)

if __name__ == "__main__":
    main()

