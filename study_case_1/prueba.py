from Bio import Entrez, SeqIO

# Configura tu correo electrónico (NCBI lo requiere)
Entrez.email = "tu_correo@example.com"

# Buscar secuencias de ARNm (utilizando identificadores NM_) de Homo sapiens
def search_mrna(term, retmax=10):
    handle = Entrez.esearch(db="protein", term=term, retmax=retmax)
    record = Entrez.read(handle)
    handle.close()
    
    id_list = record["IdList"]
    print(f"Encontrados {len(id_list)} resultados.")
    return id_list


def fetch_sequences(id_list):
    ids = ",".join(id_list)
    handle = Entrez.efetch(db="protein", id=ids, rettype="fasta", retmode="text")
    
    # Parsear las secuencias en formato FASTA
    sequences = list(SeqIO.parse(handle, "fasta"))
    handle.close()
    
    return sequences

# Buscar secuencias de ARNm de Homo sapiens (o cualquier organismo)
mrna_ids = search_mrna("Homo sapiens[ORGN] AND NM_", retmax=5)

# Obtener las secuencias en formato FASTA
sequences = fetch_sequences(mrna_ids)

# Mostrar las secuencias obtenidas
for seq_record in sequences:
    print(f"> {seq_record.id}")
    print(seq_record.seq)
